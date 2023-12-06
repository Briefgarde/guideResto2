package ch.hearc.ig.guideresto.persistence.hibernate;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, Character>{

    @Override
    public Character convertToDatabaseColumn(Boolean aBoolean) {
        if (aBoolean != null){
            return aBoolean ? 'T' : 'F';
        }
        return null;
    }

    @Override
    public Boolean convertToEntityAttribute(Character character) {
        if (character != null){
            return character.equals('T');
        }
        return null;
    }
}
