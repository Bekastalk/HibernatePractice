package bekks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "book_generator")
    @SequenceGenerator(
            name="book_generator",
            sequenceName ="book_seq_generator",
            allocationSize = 1
    )
    private Long id;
    private String name;
    private String author;
    private BigDecimal price;

    public Books(String name, String author, BigDecimal price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
