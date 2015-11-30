package org.manu.springjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Converter to convert from java.time.LocalDate to java.sql.Date and back.
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {
   private static final Logger LOGGER = LoggerFactory.getLogger(LocalDateConverter.class);
   @Override
   public Date convertToDatabaseColumn(LocalDate localDate) {
      LOGGER.info("Converting localdate to sql date");
      return null == localDate ? null : Date.valueOf(localDate);
   }
   @Override
   public LocalDate convertToEntityAttribute(Date date) {
      LOGGER.info("Converting sql date to localdate");
      return null == date ? null : date.toLocalDate();
   }
}
