package PartB.src;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MemberManager {
    private List<Member> members;
    private DefaultTableModel tableModel;
    private JTable memberTable;

    public MemberManager() {
        members = new ArrayList<>();
        String[] columns = {"Name", "Member ID"};
        tableModel = new DefaultTableModel(columns, 0);
        memberTable = new JTable(tableModel);
    }

    public void viewMembersScreen() {
        JFrame frame = new JFrame("View Members");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        updateTableData();
        JScrollPane scrollPane = new JScrollPane(memberTable);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    public void addMember(String name, String memberId) {
        Member member = new Member(name, memberId);
        members.add(member);
        updateTableData();
    }

    public void removeMember(String memberId) {
        members.removeIf(member -> member.getMemberId().equals(memberId));
        updateTableData();
    }

    private void updateTableData() {
        tableModel.setRowCount(0);
        for (Member member : members) {
            Object[] row = {
                member.getName(),
                member.getMemberId()
            };
            tableModel.addRow(row);
        }
    }

    public JTable getMemberTable() {
        return memberTable;
    }
}