package com.example.demo.bounded_context.solution.service;

import com.example.demo.base.exception.CustomException;
import com.example.demo.base.exception.ExceptionCode;
import com.example.demo.bounded_context.account.entity.Account;
import com.example.demo.bounded_context.solution.entity.ContributedCreationState;
import com.example.demo.bounded_context.solution.entity.Waste;
import com.example.demo.bounded_context.solution.repository.WasteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class WasteService {
    private final WasteRepository wasteRepository;

    @Transactional(readOnly = true)
    public Waste findById(Long id){
        return wasteRepository.findById(id)
                .orElseThrow(() -> new CustomException(ExceptionCode.WASTE_NOT_FOUND));
    }

    /**
     * 폐기물 이름 검색
     */
    @Transactional(readOnly = true)
    public Optional<Long> findByName(String name){
        log.info("SELECT FROM WASTE");
        return wasteRepository.findIdByName(name);
    }

    /**
     * 카테고리에 따른 폐기물 목록 출력
     */
    @Transactional(readOnly = true)
    public List<Waste> findFetchByCategory(String name){
        log.info("select from Waste join category");
        return wasteRepository.findByCategoriesName(name);
    }

    /**
     * 배출방법 모든 정보 출력
     */
    @Transactional(readOnly = true)
    public Waste findFetchById(Long id){
        log.info("select from Waste Join Category join Tag join Wiki");
        return wasteRepository.findFetchById(id);
    }

    @Transactional
    public Waste create(Account account, Waste waste){
        waste.setWriter(account);
        return wasteRepository.save(waste);
    }

    @Transactional
    public Waste accept(Long wasteId){
        Waste foundWaste = findById(wasteId);
        foundWaste.accept();
        return foundWaste;
    }

    @Transactional
    public Waste reject(Long wasteId){
        Waste foundWaste = findById(wasteId);
        foundWaste.reject();
        return foundWaste;
    }

    @Transactional(readOnly = true)
    public Page<Waste> findByAccountIdAndStateWithPaging(Long accountId, ContributedCreationState state, Pageable pageable){
        return wasteRepository.findByAccountIdAndState(accountId, state, pageable);
    }
    @Transactional(readOnly = true)
    public Page<Waste> findAllFetchWithPaging(Pageable pageable){
        return wasteRepository.findAllFetchByPage(pageable);
    }
}
