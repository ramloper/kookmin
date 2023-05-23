package org.kookmin.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.kookmin.demo.common.RentalStatus;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.domain.Member;
import org.kookmin.demo.domain.Rental;
import org.kookmin.demo.dto.request.rental.RentalSaveDTO;
import org.kookmin.demo.repository.EducationRepository;
import org.kookmin.demo.repository.MemberRepository;
import org.kookmin.demo.repository.RentalRepository;
import org.kookmin.demo.service.RentalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final MemberRepository memberRepository;
    private final EducationRepository educationRepository;
    @Override
    public void rentalSave(RentalSaveDTO dto) {
        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow();
        Education education = educationRepository.findById(dto.getEducationId()).orElseThrow();
        rentalRepository.save(dto.toEntity(member, education));
    }

    @Override
    public Rental rentalDetail() {
        return null;
    }

    @Override
    public List<Rental> rentalList() {
        List<Rental> rentalList = rentalRepository.findAllByStatus(RentalStatus.WAITING);
        return rentalList;
    }
    @Override
    public List<Rental> myRentalList(String username){
        List<Rental> rentalList = rentalRepository.findMyAllById(username);
        return rentalList;
    }

    @Override
    public void rentalUpdate() {

    }

    @Override
    @Transactional
    public void rentalDelete(Integer id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        rental.setStatus(RentalStatus.DELETED);
    }

    @Transactional
    @Override
    public void okRental(Integer id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        rental.setStatus(RentalStatus.RESERVED);
    }

    public LocalDate returnDay(){
        /**
         * 예상 반납일자 로직 구상해서 구현 예정
         * */
        return null;
    }
}
