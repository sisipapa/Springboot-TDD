package com.sisipapa.study.tdd.dto;

import lombok.ToString;

import java.util.Objects;

@ToString
public class PersonGroup {

    public String country;
    public String city;
    public boolean female;

    public PersonGroup(Person p) {
        this.country = p.getCountry();
        this.city = p.getCity();
        this.female = p.isFemale();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PersonGroup)) {
            return false;
        }

        PersonGroup that = (PersonGroup) object;
        return Objects.equals(this.country, that.country) &&
                Objects.equals(this.city, that.city) &&
                Objects.equals(this.female, that.female);

    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, female);
    }
}
