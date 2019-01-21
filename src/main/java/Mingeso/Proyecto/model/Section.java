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

        @Column(name = "day")
        private int dayOfWeek;

        @Column(name ="time_start")
        private Time timeStart;

        @Column(name ="time_end")
        private Time timeEnd;

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

        public int getDayOfWeek() {
                return dayOfWeek;
        }

        public void setDayOfWeek(int dayOfWeek) {
                this.dayOfWeek = dayOfWeek;
        }

        public Time getTimeStart() {
                return timeStart;
        }

        public void setTimeStart(Time timeStart) {
                this.timeStart = timeStart;
        }

        public Time getTimeEnd() {
                return timeEnd;
        }

        public void setTimeEnd(Time timeEnd) {
                this.timeEnd = timeEnd;
        }
}
