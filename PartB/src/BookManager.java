package PartB.src;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookManager {
    private List<Book> availableBooks;
    private List<Book> borrowedBooks;
    private DefaultTableModel tableModel;
    private JTable bookTable;

    public BookManager() {
        availableBooks = new ArrayList<>();
        borrowedBooks = new ArrayList<>();
        String[] columns = {"Title", "Author", "ISBN", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        bookTable = new JTable(tableModel);
    }

    public void addBook(String title, String author, String isbn) {
        Book book = new Book(title, author, isbn);
        availableBooks.add(book);
        updateTableData();
    }

    public void removeBook(String isbn) {
        availableBooks.removeIf(book -> book.getIsbn().equals(isbn));
        borrowedBooks.removeIf(book -> book.getIsbn().equals(isbn));
        updateTableData();
    }

    public void borrowBook(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid ISBN", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Book bookToBorrow = null;
        for (Book book : availableBooks) {
            if (book.getIsbn().equals(isbn.trim())) {
                bookToBorrow = book;
                break;
            }
        }

        if (bookToBorrow != null) {
            availableBooks.remove(bookToBorrow);
            borrowedBooks.add(bookToBorrow);
            bookToBorrow.setAvailable(false);
            updateTableData();
            JOptionPane.showMessageDialog(null, 
                String.format("Book '%s' borrowed successfully!", bookToBorrow.getTitle()));
        } else {
            JOptionPane.showMessageDialog(null, 
                "No available book found with ISBN: " + isbn,
                "Not Found", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void returnBook(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid ISBN", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Book bookToReturn = null;
        for (Book book : borrowedBooks) {
            if (book.getIsbn().equals(isbn.trim())) {
                bookToReturn = book;
                break;
            }
        }

        if (bookToReturn != null) {
            borrowedBooks.remove(bookToReturn);
            availableBooks.add(bookToReturn);
            bookToReturn.setAvailable(true);
            updateTableData();
            JOptionPane.showMessageDialog(null, 
                String.format("Book '%s' returned successfully!", bookToReturn.getTitle()));
        } else {
            JOptionPane.showMessageDialog(null, 
                "No borrowed book found with ISBN: " + isbn,
                "Not Found", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTableData() {
        tableModel.setRowCount(0);
        
        for (Book book : availableBooks) {
            Object[] row = {
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                "Available"
            };
            tableModel.addRow(row);
        }
    
        for (Book book : borrowedBooks) {
            Object[] row = {
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                "Borrowed"
            };
            tableModel.addRow(row);
        }
    }

    public JTable getBookTable() {
        return bookTable;
    }
}