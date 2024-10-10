package br.com.matheus.data.vo.v1;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookVO extends RepresentationModel<BookVO> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long Key;
	private String author;
	private LocalDateTime datetime;
	private Double price;
	private String title;
	
	public BookVO () {
		
	}

	public Long getKey() {
		return Key;
	}

	public void setKey(Long key) {
		Key = key;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(Key, author, datetime, price, title);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookVO other = (BookVO) obj;
		return Objects.equals(Key, other.Key) && Objects.equals(author, other.author)
				&& Objects.equals(datetime, other.datetime) && Objects.equals(price, other.price)
				&& Objects.equals(title, other.title);
	}
	
	
}
