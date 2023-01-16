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

	public List<TaskModel7> task7() {
		return jdbcTemplate.query(
				"select infrastructure.id as id,\n" +
						"       count(m.id)       as amount\n" +
						"from infrastructure\n" +
						"         left join infrastruct2unit i2u on infrastructure.id = i2u.struct_id\n" +
						"         right join milunit m on m.id = i2u.unit_id\n" +
						"group by id\n" +
						"having count(m.id) = 1",
				new TaskModel7.Mapper()
		);
	}

	public List<TaskModel8> task8() {
		return jdbcTemplate.query(
				"SELECT milunit_number as num, " +
						"t2u.amount as amount\n" +
						"from milunit left join tech2unit t2u on milunit.id = t2u.unit_id\n" +
						"left join militech m on m.id = t2u.tech_id\n" +
						"WHERE t2u.amount > 5",
				new TaskModel8.Mapper()
		);

	}

	public List<TaskModel9> task9() {
		return jdbcTemplate.query(
				"SELECT m.tech_type   AS type,\n" +
						"       m.description AS descr,\n" +
						"       t2u.amount    AS amount,\n" +
						"       m2.name       AS disname\n" +
						"FROM milunit\n" +
						"\t     LEFT JOIN tech2unit t2u ON milunit.id = t2u.unit_id\n" +
						"\t     LEFT JOIN militech m\n" +
						"\t               ON m.id = t2u.tech_id\n" +
						"\t     LEFT JOIN mildistrict m2 ON milunit.mildistrict_id = m2.id\n",
				new TaskModel9.Mapper()
		);
	}

	public List<TaskModel10> task10() {
		return jdbcTemplate.query(
				"SELECT major, COUNT(o.id) as amount\n" +
						"FROM mildistrict\n" +
						"\t     LEFT JOIN milunit m ON mildistrict.id = m.mildistrict_id\n" +
						"\t     LEFT JOIN officers o ON o.id = m.commander_id\n" +
						"GROUP BY major\n" +
						"HAVING amount > 1",
				new TaskModel10.Mapper()
		);
	}

	public List<TaskModel11> task11() {
		List<TaskModel11> query = jdbcTemplate.query(
				"SELECT name, fullname, major, milunit_number\n" +
						"FROM mildistrict\n" +
						"\t     LEFT JOIN milunit m ON mildistrict.id = m.mildistrict_id\n" +
						"\t     LEFT JOIN officers o ON o.id = m.commander_id\n" +
						"WHERE commander_id IS NOT NULL",
				new TaskModel11.Mapper()
		);
		return query;
	}

	public List<TaskModel11> task11filterMajor(String major) {
		return jdbcTemplate.query(
				"SELECT name, fullname, major, milunit_number\n" +
						"FROM mildistrict\n" +
						"\t     LEFT JOIN milunit m ON mildistrict.id = m.mildistrict_id\n" +
						"\t     LEFT JOIN officers o ON o.id = m.commander_id\n" +
						"WHERE commander_id IS NOT NULL " +
						"AND major=?",
				new Object[]{major},
				new TaskModel11.Mapper()
		);
	}

	public List<TaskModel11> task11filterDistrict(String district) {
		return jdbcTemplate.query(
				"SELECT name, fullname, major, milunit_number\n" +
						"FROM mildistrict\n" +
						"\t     LEFT JOIN milunit m ON mildistrict.id = m.mildistrict_id\n" +
						"\t     LEFT JOIN officers o ON o.id = m.commander_id\n" +
						"WHERE commander_id IS NOT NULL " +
						"AND name=?",
				new Object[]{district},
				new TaskModel11.Mapper()
		);
	}

	public List<TaskModel11> task11filter(String major, String district) {
		return jdbcTemplate.query(
				"SELECT name, fullname, major, milunit_number\n" +
						"FROM mildistrict\n" +
						"\t     LEFT JOIN milunit m ON mildistrict.id = m.mildistrict_id\n" +
						"\t     LEFT JOIN officers o ON o.id = m.commander_id\n" +
						"WHERE commander_id IS NOT NULL " +
						"AND name=? AND major=?",
				new Object[]{district, major},
				new TaskModel11.Mapper()
		);
	}

	public List<TaskModel12> task12() {
		return jdbcTemplate.query(
				"SELECT milunit.id  AS id,\n" +
						"       m.tech_type AS type,\n" +
						"       t2u.amount  AS amount\n" +
						"FROM milunit\n" +
						"\t     LEFT JOIN tech2unit t2u ON milunit.id = t2u.unit_id\n" +
						"\t     LEFT JOIN militech m ON m.id = t2u.tech_id\n" +
						"WHERE t2u.amount > 10;",
				new TaskModel12.Mapper()
		);
	}

	public List<TaskModel12> task12filtered(String type) {
		return jdbcTemplate.query(
				"SELECT milunit.id  AS id,\n" +
						"       m.tech_type AS type,\n" +
						"       t2u.amount  AS amount\n" +
						"FROM milunit\n" +
						"\t     LEFT JOIN tech2unit t2u ON milunit.id = t2u.unit_id\n" +
						"\t     LEFT JOIN militech m ON m.id = t2u.tech_id\n" +
						"WHERE t2u.amount > 10 and tech_type=?;",
				new Object[]{type},
				new TaskModel12.Mapper()
		);
	}


	public TaskModel13 task13() {
		return jdbcTemplate.query(
				"SELECT aid as id, COUNT(tid) AS amount\n" +
						"FROM (SELECT army.id AS aid, t.id AS tid\n" +
						"      FROM army\n" +
						"\t           LEFT JOIN tacticunit t ON army.id = t.army_id\n" +
						"\t           LEFT JOIN milunit m ON t.id = m.tacit_unit_id) AS res\n" +
						"GROUP BY aid\n" +
						"HAVING amount <> 0\n" +
						"ORDER BY amount DESC\n" +
						"LIMIT 1",
				new TaskModel13.Mapper()
		).get(0);
	}

	public List<TaskModel8> task8filtered(String type) {
		return jdbcTemplate.query(
				"SELECT milunit_number as num, " +
						"t2u.amount as amount\n" +
						"from milunit left join tech2unit t2u on milunit.id = t2u.unit_id\n" +
						"left join militech m on m.id = t2u.tech_id\n" +
						"WHERE tech_type=? AND t2u.amount > 5",
				new Object[]{type},
				new TaskModel8.Mapper()
		);


	}

	public List<TaskModel9> task9filtered(String type) {
		return jdbcTemplate.query(
				"SELECT m.tech_type   AS type,\n" +
						"       m.description AS descr,\n" +
						"       t2u.amount    AS amount,\n" +
						"       m2.name       AS disname\n" +
						"FROM milunit\n" +
						"\t     LEFT JOIN tech2unit t2u ON milunit.id = t2u.unit_id\n" +
						"\t     LEFT JOIN militech m\n" +
						"\t               ON m.id = t2u.tech_id\n" +
						"\t     LEFT JOIN mildistrict m2 ON milunit.mildistrict_id = m2.id\n" +
						"WHERE tech_type = ?",
				new Object[]{type},
				new TaskModel9.Mapper()
		);
	}

	public TaskModel13 task13low() {
		return jdbcTemplate.query(
				"SELECT aid as id, COUNT(tid) AS amount\n" +
						"FROM (SELECT army.id AS aid, t.id AS tid\n" +
						"      FROM army\n" +
						"\t           LEFT JOIN tacticunit t ON army.id = t.army_id\n" +
						"\t           LEFT JOIN milunit m ON t.id = m.tacit_unit_id) AS res\n" +
						"GROUP BY aid\n" +
						"HAVING amount <> 0\n" +
						"ORDER BY amount\n" +
						"LIMIT 1",
				new TaskModel13.Mapper()
		).get(0);

	}
}
