import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProductMenu extends JFrame {

    public static void main(String[] args) {
        ProductMenu menu = new ProductMenu();
        menu.setSize(700, 600);
        menu.setLocationRelativeTo(null);
        menu.setContentPane(menu.mainPanel);
        menu.getContentPane().setBackground(Color.WHITE);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private int selectedIndex = -1;

    private JPanel mainPanel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField hargaField;
    private JComboBox<String> kategoriComboBox;
    private JTable productTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JComboBox<String> comboBox1;


    // -------------------- CONSTRUCTOR --------------------
    public ProductMenu() {
        productTable.setModel(setTable());
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        String[] kategoriData = {"Arabica", "Robusta", "Liberica"};
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        deleteButton.setVisible(false);

        // Tombol Add / Update
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });

        // Tombol Delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Apakah kamu yakin ingin menghapus data kopi ini?",
                        "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteData();
                }
            }
        });

        // Tombol Cancel
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // Klik tabel
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedIndex = productTable.getSelectedRow();

                if (selectedIndex != -1) {
                    idField.setText(productTable.getValueAt(selectedIndex, 1).toString());
                    namaField.setText(productTable.getValueAt(selectedIndex, 2).toString());
                    hargaField.setText(productTable.getValueAt(selectedIndex, 3).toString());
                    kategoriComboBox.setSelectedItem(productTable.getValueAt(selectedIndex, 4).toString());

                    addUpdateButton.setText("Update");
                    deleteButton.setVisible(true);
                }
            }
        });
    }

    // -------------------- KONEKSI DATABASE --------------------
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_product_menu",
                    "root",
                    ""
            );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e.getMessage());
            return null;
        }
    }

    // -------------------- MENAMPILKAN DATA --------------------
    public final DefaultTableModel setTable() {
        Object[] kolom = {"No", "ID", "Nama Kopi", "Harga", "Kategori"};
        DefaultTableModel model = new DefaultTableModel(null, kolom);

        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produk");

            int no = 1;
            while (rs.next()) {
                Object[] row = {
                        no++,
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getDouble("harga"),
                        rs.getString("kategori")
                };
                model.addRow(row);
            }

            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage());
        }

        return model;
    }

    // -------------------- TAMBAH DATA --------------------
    public void insertData() {
        String id = idField.getText();
        String nama = namaField.getText();
        String hargaText = hargaField.getText();

        if (id.isEmpty() || nama.isEmpty() || hargaText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double harga = Double.parseDouble(hargaText);
            String kategori = kategoriComboBox.getSelectedItem().toString();

            Connection conn = getConnection();

            // Cek apakah ID sudah ada
            PreparedStatement checkStmt = conn.prepareStatement("SELECT id FROM produk WHERE id = ?");
            checkStmt.setString(1, id);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "ID sudah ada di database!", "Error", JOptionPane.ERROR_MESSAGE);
                conn.close();
                return;
            }

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO produk VALUES (?, ?, ?, ?)");
            stmt.setString(1, id);
            stmt.setString(2, nama);
            stmt.setDouble(3, harga);
            stmt.setString(4, kategori);
            stmt.executeUpdate();

            conn.close();
            productTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(this, "Data kopi berhasil ditambahkan!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menambah data: " + e.getMessage());
        }
    }

    // -------------------- UPDATE DATA --------------------
    public void updateData() {
        String id = idField.getText();
        String nama = namaField.getText();
        String hargaText = hargaField.getText();

        if (id.isEmpty() || nama.isEmpty() || hargaText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double harga = Double.parseDouble(hargaText);
            String kategori = kategoriComboBox.getSelectedItem().toString();

            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE produk SET nama=?, harga=?, kategori=? WHERE id=?");
            stmt.setString(1, nama);
            stmt.setDouble(2, harga);
            stmt.setString(3, kategori);
            stmt.setString(4, id);
            stmt.executeUpdate();

            conn.close();
            productTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(this, "Data kopi berhasil diperbarui!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui data: " + e.getMessage());
        }
    }

    // -------------------- HAPUS DATA --------------------
    public void deleteData() {
        String id = idField.getText();
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM produk WHERE id = ?");
            stmt.setString(1, id);
            stmt.executeUpdate();
            conn.close();

            productTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(this, "Data kopi berhasil dihapus!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage());
        }
    }

    // -------------------- BERSIHKAN FORM --------------------
    public void clearForm() {
        idField.setText("");
        namaField.setText("");
        hargaField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        selectedIndex = -1;
    }
}
