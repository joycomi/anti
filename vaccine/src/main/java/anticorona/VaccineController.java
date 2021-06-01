package anticorona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 @RestController
 public class VaccineController {
     private Logger logger = LoggerFactory.getLogger(this.getClass());

     @Autowired
     VaccineRepository vaccineRepository;

     @RequestMapping(value = "/vaccines/checkAndBookStock",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8")
    public boolean checkAndBookStock(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("##### /vaccine/checkAndBookStock  called #####");

        logger.trace("-----trace-----");
        logger.debug("-----debug-----");
        logger.info("-----info-----");
        logger.warn("-----warn-----");
        logger.error("-----error-----");

        boolean status = false;

        Long vaccineId = Long.valueOf(request.getParameter("vaccineId"));
        
        Vaccine vaccine = vaccineRepository.findByVaccineId(vaccineId);
        //예약 가능한지 체크 
        if(vaccine.getStock() - vaccine.getBookQty() > 0) {
            //예약 가능하면 예약수량 증가
            status = true;
            vaccine.setBookQty(vaccine.getBookQty() + 1);
            vaccineRepository.save(vaccine);
        }

        return status;
     }
 }
