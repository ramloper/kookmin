package org.kookmin.demo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.kookmin.demo.common.EducationStatus;
import org.kookmin.demo.common.RentalStatus;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.domain.Member;
import org.kookmin.demo.domain.Rental;
import org.kookmin.demo.dto.request.rental.RentalSaveDTO;
import org.kookmin.demo.dto.sms.MessageDTO;
import org.kookmin.demo.repository.EducationRepository;
import org.kookmin.demo.repository.MemberRepository;
import org.kookmin.demo.repository.RentalRepository;
import org.kookmin.demo.service.RentalService;
import org.kookmin.demo.service.SmsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final MemberRepository memberRepository;
    private final EducationRepository educationRepository;
    private final SmsService smsService;
    @Override
    public void rentalSave(RentalSaveDTO dto) {
        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow();
        Education education = educationRepository.findById(dto.getEducationId()).orElseThrow();
        education.setStatus(EducationStatus.UNAVAILABLE);
        rentalRepository.save(dto.toEntity(member, education));
    }

    @Override
    public Rental rentalDetail() {
        return null;
    }

    @Override
    public List<Rental> rentalListWaiting() {
        List<Rental> rentalList = rentalRepository.findAllByStatus(RentalStatus.WAITING);
        return rentalList;
    }

    public List<Rental> rentalListReserved() {
        List<Rental> rentalList = rentalRepository.findAllByStatus(RentalStatus.RESERVED);
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
    public void rentalCancel(Integer id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        rental.getEducation().setStatus(EducationStatus.AVAILABLE);

        rentalRepository.delete(rental);

    }


    @Transactional
    @Override
    public void okRental(Integer id) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        String content = rental.getEducation().getBookName() + "\n" +"대여 날짜 : " + rental.getRentalDate() + "\n" + "반납 날짜 : " + rental.getReturnDate();
        MessageDTO messageDTO = MessageDTO.builder()
                .to(rental.getMember().getPhoneNumber())
                .content(content)
                .build();
        smsService.sendSms(messageDTO);
        rental.setStatus(RentalStatus.RESERVED);
    }

    public LocalDate returnDay(){
        /**
         * 예상 반납일자 로직 구상해서 구현 예정
         * */
        return null;
    }
}
