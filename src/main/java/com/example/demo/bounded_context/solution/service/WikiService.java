package com.example.demo.bounded_context.solution.service;

import com.example.demo.base.exception.CustomException;
import com.example.demo.base.exception.ExceptionCode;
import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.solution.dto.WikiRequest;
import com.example.demo.bounded_context.solution.entity.Category;
import com.example.demo.bounded_context.solution.entity.Wiki;
import com.example.demo.bounded_context.solution.entity.Tag;
import com.example.demo.bounded_context.solution.entity.Waste;
import com.example.demo.bounded_context.solution.repository.WikiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Boolean isExistNonAccept(Account writer, Waste waste){
        log.info("select from Wiki join Waste");
        return wikiRepository.existsByWriterAndWasteAndIsAcceptFalse(writer, waste);
    }

    @Transactional(readOnly = true)
    public Wiki read(Long wikiId){
        return fetchFindById(wikiId);
    }

    @Transactional
    public Wiki create(Account writer, Waste waste, WikiRequest request){
        log.info("위키 생성");
        if(isExistNonAccept(writer, waste)) throw new CustomException(ExceptionCode.EXIST_WIKI);
        Wiki wiki = request.toEntity(writer, waste);
        return wikiRepository.save(wiki);
    }

    /**
     * delete insert 여러번 문제 수정 필요
     */
    @Transactional
    public void accept(Long wikiId){
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
}
