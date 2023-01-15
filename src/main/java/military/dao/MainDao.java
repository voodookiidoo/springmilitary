package military.dao;

import military.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MainDao {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public MainDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<TaskModel1> task1() {
		return jdbcTemplate.query(
				"SELECT army.id AS 'army_id',\n" +
						"       t.id    AS 'tactic_unit_id',\n" +
						"       m.id    AS 'military_unit_id',\n" +
						"       if(fullname is NULL,'NO COMMANDER', fullname) as 'commander'\n" +
						"FROM army\n" +
						"\t     LEFT JOIN tacticunit t ON army.id = t.army_id\n" +
						"\t     LEFT JOIN milunit m ON m.tacit_unit_id = t.id\n" +
						"\t     LEFT JOIN officers o ON o.id = m.commander_id = o.id;",
				new TaskModel1.Mapper());
	}

	public List<TaskModel1> task1filtered(Integer number) {
		return jdbcTemplate.query(
				"SELECT army.id AS 'army_id',\n" +
						"       t.id    AS 'tactic_unit_id',\n" +
						"       m.id    AS 'military_unit_id',\n" +
						"       if(fullname is NULL,'NO COMMANDER', fullname) as 'commander'\n" +
						"FROM army\n" +
						"\t     LEFT JOIN tacticunit t ON army.id = t.army_id\n" +
						"\t     LEFT JOIN milunit m ON m.tacit_unit_id = t.id\n" +
						"\t     LEFT JOIN officers o ON o.id = m.commander_id = o.id\n" +
						"WHERE army_number = ?;",
				new Object[]{number},
				new TaskModel1.Mapper());
	}

	public List<TaskModel2> task2() {
		return jdbcTemplate.query(
				"SELECT milunit.id as id,\n " +
						"       o.fullname as name \n" +
						"FROM milunit\n" +
						"left JOIN officers o on commander_id=o.id",
				new TaskModel2.Mapper()
		);
	}

	public List<TaskModel2> task2filtered(String rank) {
		return jdbcTemplate.query(
				"SELECT milunit.id as id,\n " +
						"       o.fullname as name \n" +
						"FROM milunit\n" +
						"left JOIN officers o on commander_id=o.id\n" +
						"WHERE military_rank=?",
				new Object[]{rank},
				new TaskModel2.Mapper()
		);

	}

	public List<TaskModel3> task3() {
		return jdbcTemplate.query(
				"SELECT milunit.id as id,\n " +
						"       o.fullname as name \n" +
						"FROM milunit\n" +
						"left JOIN officers o on commander_id=o.id",
				new TaskModel3.Mapper()
		);
	}

	public List<TaskModel3> task3filtered(String type) {
		return jdbcTemplate.query(
				"SELECT milunit.id as id,\n " +
						"       o.fullname as name \n" +
						"FROM milunit\n" +
						"left JOIN officers o on commander_id=o.id\n" +
						"WHERE comm_type=?",
				new Object[]{type},
				new TaskModel3.Mapper()
		);


	}

	public List<TaskModel5> task5() {
		return jdbcTemplate.query(
				"SELECT milunit_number as 'mil_num',place\n" +
						"FROM mildistrict right JOIN milunit m ON mildistrict.id = m.mildistrict_id;",
				new TaskModel5.Mapper()
		);
	}

	public List<TaskModel6> task6() {
		return jdbcTemplate.query(
				"select milunit.id, t2u.amount, tech_type, milunit_number " +
						"FROM milunit LEFT JOIN tech2unit t2u ON milunit.id = t2u.unit_id " +
						"LEFT JOIN militech m ON m.id = t2u.tech_id;",
				new TaskModel6.Mapper()
		);
	}

	public List<TaskModel6> task6(Integer num) {
		return jdbcTemplate.query(
				"select milunit.id, t2u.amount, tech_type, milunit_number " +
						"FROM milunit LEFT JOIN tech2unit t2u ON milunit.id = t2u.unit_id " +
						"LEFT JOIN militech m ON m.id = t2u.tech_id " +
						"WHERE milunit_number=?",
				new Object[]{num},
				new TaskModel6.Mapper()
		);

	}

	public List<TaskModel6> task6(String type) {
		return jdbcTemplate.query(
				"select milunit.id, t2u.amount, tech_type, milunit_number " +
						"FROM milunit LEFT JOIN tech2unit t2u ON milunit.id = t2u.unit_id " +
						"LEFT JOIN militech m ON m.id = t2u.tech_id " +
						"WHERE tech_type=?",
				new Object[]{type},
				new TaskModel6.Mapper()
		);

	}

	public List<TaskModel6> task6(String type, Integer num) {
		return jdbcTemplate.query(
				"select milunit.id, t2u.amount, tech_type, milunit_number " +
						"FROM milunit LEFT JOIN tech2unit t2u ON milunit.id = t2u.unit_id " +
						"LEFT JOIN militech m ON m.id = t2u.tech_id " +
						"WHERE milunit_number=? AND tech_type=?",
				new Object[]{num, type},
				new TaskModel6.Mapper()
		);
	}
}
