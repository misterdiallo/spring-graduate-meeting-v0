package com.linyiuniversity.graduate.microservices.userservices.data.Leaders;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity()
@Table(name="leaders")
public class LeaderEntity implements Serializable {
    /*
    *
    */
    private static final long serialVersionUID = -3445345300907450984L;


    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id; // only Here

    @Column(unique = true, nullable = false)
    private String USER_ID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
}
