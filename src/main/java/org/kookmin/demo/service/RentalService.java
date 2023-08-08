package org.kookmin.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.kookmin.demo.domain.Education;
import org.kookmin.demo.domain.Member;
import org.kookmin.demo.domain.Rental;
import org.kookmin.demo.dto.request.rental.RentalSaveDTO;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

public interface RentalService {

    void rentalSave(RentalSaveDTO dto);

    Rental rentalDetail();

    List<Rental> rentalList();

    List<Rental> myRentalList(String username);

    void rentalUpdate();

    void rentalDelete(Integer id);

    void okRental(Integer id) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException;
    public LocalDate returnDay();
}
