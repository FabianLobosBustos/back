package Mingeso.Proyecto.model;

import lombok.Data;

import java.sql.Time;
import java.util.List;

import javax.persistence.*;
@Data
@Entity
@Table(name = "section")
public class Section {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(name = "code")
        private String code;

        @Column(name = "horario")
        private int horario;

        @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Student> students;

        public Long getId() {
                return id;
        }

        public String getCode() {
                return code;
        }

        public void setCode(String code) {
                this.code = code;
        }


        public int getHorario() {
                return horario;
        }

        public void setHorario(int horario) {
                this.horario = horario;
        }
}
