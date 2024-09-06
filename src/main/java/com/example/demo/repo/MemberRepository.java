package com.example.demo.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Member> findAll() {
        String sql = "SELECT * FROM members";
        return jdbcTemplate.query(sql, new MemberRowMapper());
    }

    public Member findById(int id) {
        String sql = "SELECT * FROM members WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new MemberRowMapper(), id);
    }

    public void save(Member member) {
        String sql = "INSERT INTO members (name, phone, registered_date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, member.getName(), member.getPhone(), member.getRegisteredDate());
    }

    public void update(Member member) {
        String sql = "UPDATE members SET name = ?, phone = ?, registered_date = ? WHERE id = ?";
        jdbcTemplate.update(sql, member.getName(), member.getPhone(), member.getRegisteredDate(), member.getId());
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM members WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setId(rs.getInt("id"));
            member.setName(rs.getString("name"));
            member.setPhone(rs.getString("phone"));
            member.setRegisteredDate(rs.getDate("registered_date").toLocalDate());
            return member;
        }
    }
}
