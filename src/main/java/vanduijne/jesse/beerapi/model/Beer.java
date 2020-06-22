package vanduijne.jesse.beerapi.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"brewery", "type", "name"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Beer {

    @Id
    @SequenceGenerator(name = "beer_seq", initialValue = 1000001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "beer_seq")
    private long id;
    private String brewery;
    private String type;
    private String name;
    private double percentage;
    private double price;

    @OneToOne(mappedBy = "beer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "beer_id")
    private Stock stock;

    public Beer(){

    }

    public Beer(String brewery, String type, String name, double percentage, double price) {
        this.brewery = brewery;
        this.type = type;
        this.name = name;
        this.percentage = percentage;
        this.price = price;
    }


    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getBrewery() { return brewery; }

    public void setBrand(String brewery) { this.brewery = brewery; }

    public String getType() { return type; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public void setType(String type) { this.type = type; }

    public double getPercentage() { return percentage; }

    public void setPercentage(double percentage) {
        if(percentage > 20) throw new IllegalArgumentException("Beer shouldn't contain more than 20% alcohol. That's dangerous");
        this.percentage = percentage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Beer{");
        sb.append("id=").append(id);
        sb.append(", brewery='").append(brewery).append('\'');
        sb.append(", type=").append(type).append('\'');
        sb.append(", name=").append(name).append('\'');
        sb.append(", percentage='").append(percentage).append('\'');
        sb.append('}');
        return sb.toString();
    }

}


