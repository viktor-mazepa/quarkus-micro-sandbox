package com.mazasoft.service.book.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Entity
@Table(name = "t_books")
public class Book extends PanacheEntity {

    @Column(length = 50, nullable = false)
    public String isbn13;
    @Column(length = 200, nullable = false)
    public String title;
    @Column(length = 100, nullable = false)
    public String author;
    @Column(name = "year_of_publication", nullable = false)
    public int yearOfPublication;
    @Column(length = 50)
    public String genre;
    @Column(name = "generation_date")
    public Instant generationDate;

    public Book() {
        generationDate = Instant.now();
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Instant getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(Instant generationDate) {
        this.generationDate = generationDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", genre='" + genre + '\'' +
                ", generationDate=" + generationDate +
                ", id=" + id +
                '}';
    }
}
