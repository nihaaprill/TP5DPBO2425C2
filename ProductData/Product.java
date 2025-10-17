public class Product {
    private String id;
    private String nama;
    private double harga;
    private String jenisBiji;

    public Product(String id, String nama, double harga, String jenisBiji) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.jenisBiji = jenisBiji;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getJenisBiji() {
        return jenisBiji;
    }

    public void setJenisBiji(String jenisBiji) {
        this.jenisBiji = jenisBiji;
    }
}
