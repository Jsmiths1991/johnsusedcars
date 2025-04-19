package bo;

import java.util.Vector;

/**
 * Class representing the Customer relation in the database
 *
 */
public class Customer {
    
    private int id;
    private String name;
    private int phone;
    private String email;

    public Customer(int id, String name, int phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Useful function when you need to represent a Customer as a human-readable
     * string (e.g. in a list or combo box)
     * @return A human-readable string representation of a Customer
     */
    @Override
    public String toString() {
        return String.format(" %d, %s, %d, %s", id, name, phone, email);
    }

    public Vector getRow() {
        Vector vec = new Vector();
        vec.add(this.id);
        vec.add(this.name);
        vec.add(this.phone);
        vec.add(this.email);
        return vec;
    
    }
}
