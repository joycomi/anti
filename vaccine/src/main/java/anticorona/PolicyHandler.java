package anticorona;

import anticorona.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PolicyHandler{
    @Autowired VaccineRepository vaccineRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBookingCancelled_ModifyStock(@Payload BookingCancelled bookingCancelled){

        if(!bookingCancelled.validate()) return;

        System.out.println("\n\n##### listener ModifyStock : " + bookingCancelled.toJson() + "\n\n");

        // 예약수량 감소 //
        Vaccine vaccine = vaccineRepository.findByVaccineId(bookingCancelled.getVaccineId());
        vaccine.setBookQty(vaccine.getBookQty()-1);
        vaccineRepository.save(vaccine);
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCompleted_ModifyStock(@Payload Completed completed){

        if(!completed.validate()) return;

        System.out.println("\n\n##### listener ModifyStock : " + completed.toJson() + "\n\n");

        // 재고수량 & 예약수량 감소 //
        Vaccine vaccine = vaccineRepository.findByVaccineId(completed.getVaccineId());
        vaccine.setStock(vaccine.getStock()-1);
        vaccine.setBookQty(vaccine.getBookQty()-1);
        vaccineRepository.save(vaccine);
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){
        logger.trace("-----trace-----");
        logger.debug("-----debug-----");
        logger.info("-----info-----");
        logger.warn("-----warn-----");
        logger.error("-----error-----");
    }


}
