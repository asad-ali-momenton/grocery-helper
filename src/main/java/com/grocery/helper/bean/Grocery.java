package com.grocery.helper.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grocery")
public class Grocery {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String itemName;

    private String category;

    @Override
    public String toString() {
        return "Grocery{" + "id=" + id + ", itemName='" + itemName + ", category='" + category + '}';
    }
}
