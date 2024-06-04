package com.example.demo.bounded_context.wiki.service;

import com.example.demo.base.exception.CustomException;
import com.example.demo.base.exception.ExceptionCode;
import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.wiki.dto.WikiCompareResponse;
import com.example.demo.bounded_context.wiki.dto.WikiRequest;
import com.example.demo.bounded_context.wiki.dto.WikiResponse;
import com.example.demo.bounded_context.solution.entity.*;
import com.example.demo.bounded_context.wiki.repository.WikiRepository;
import com.example.demo.bounded_context.solution.service.WasteService;
import com.example.demo.bounded_context.wiki.entity.Wiki;
import com.example.demo.bounded_context.wiki.entity.WikiState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class WikiService {
    private final WikiRepository wikiRepository;
    private final WasteService wasteService;

    @Transactional(readOnly = true)
    public Wiki findById(Long wikiId){
        return wikiRepository.findById(wikiId)
                .orElseThrow(() -> new CustomException(ExceptionCode.EMPTY_WIKI));
    }

    @Transactional(readOnly = true)
    public Wiki fetchFindById(Long wikiId){
        log.info("select from Wiki join Account");
        return wikiRepository.findFetchById(wikiId);
    }

    @Transactional(readOnly = true)
    public void isExistNonAccept(Account writer, Waste waste){
        log.info("select from Wiki join Waste");
        if(wikiRepository.existsPending(writer.getId(), waste.getId()).isPresent()){
            throw new CustomException(ExceptionCode.EXIST_WIKI);
        };
    }

    @Transactional(readOnly = true)
    public WikiCompareResponse read(Long wikiId){
        Wiki foundWiki = fetchFindById(wikiId);
        WikiResponse origin = foundWiki.getParent() == null ? null : WikiResponse.fromEntity(foundWiki.getParent());
        WikiResponse modified = WikiResponse.fromEntity(foundWiki);
        return WikiCompareResponse.of(origin, modified);
    }

    @Transactional
    public Wiki create(Account writer, Waste waste, WikiRequest request){
        log.info("위키 생성");
        isExistNonAccept(writer, waste);
        Optional<Wiki> parentWiki = wikiRepository.findByRecent(waste.getId());
        Wiki wiki = request.toEntity(writer, waste, parentWiki.orElse(null));
        return wikiRepository.save(wiki);
    }

    @Transactional
    public void delete(Long userId, Long wikiId){
        log.info("위키 삭제");
        Wiki foundWiki = verifyWikiAuthor(userId, wikiId);
        if(foundWiki.getWikiState() != WikiState.PENDING){
            throw new IllegalArgumentException("반영 및 거부된 위키는 삭제할 수 없습니다.");
        }
        wikiRepository.delete(foundWiki);
    }

    @Transactional(readOnly = true)
    public Wiki verifyWikiAuthor(Long userId, Long wikiId){
        log.info("위키 작성자 검사");
        Wiki wiki = findById(wikiId);
        if(!wiki.getWriter().getId().equals(userId))
            throw new IllegalArgumentException("해당 위키의 작성자가 아닙니다.");
        return wiki;
    }

    /**
     * delete insert 여러번 문제 수정 필요
     */
    @Transactional
    public void accept(Long wikiId){
        log.info("위키 반영");
        Wiki wiki = fetchFindById(wikiId);
        Waste waste = wasteService.findFetchById(wiki.getWaste().getId());

        waste.getTags().clear();
        waste.getCategories().clear();

        String[] categoriesSplit = wiki.getCategories().split(",");
        for(String categoryName : categoriesSplit){
            Category category = Category.builder()
                    .name(categoryName)
                    .waste(waste)
                    .build();
            waste.getCategories().add(category);
        }

        String[] tagsSplit = wiki.getTags().split(",");
        for(String tagName : tagsSplit){
            Tag tag = Tag.builder()
                    .name(tagName)
                    .waste(waste)
                    .build();
            waste.getTags().add(tag);
        }

        wiki.accept();
        waste.update(wiki.getName(), wiki.getSolution());
    }

    @Transactional
    public void reject(Long wikiId){
        Wiki foundWiki = findById(wikiId);
        foundWiki.reject();
    }
}
