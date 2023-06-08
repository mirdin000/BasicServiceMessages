package org.example.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Entity
@Table(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messages_sequence")
    @SequenceGenerator(name = "messages_sequence", sequenceName = "messages_sequence", allocationSize = 1)
    private long id;
    @Column(name = "username")
    @Size(min = 2, max = 30,message = "Error username!!!")
    @NotEmpty(message = "Empty field!!!")
    private String username;
    @Column(name = "textMessage")
    @NotEmpty(message = "Empty field!!!")
    private String textMessage;
}
