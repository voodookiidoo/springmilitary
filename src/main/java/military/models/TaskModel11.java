package military.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
public class TaskModel11 {
	private String name, fullname, major;
	private int num;

	public static class Mapper extends BeanPropertyRowMapper<TaskModel11> {
		@Override
		public TaskModel11 mapRow(ResultSet rs, int rowNumber) throws SQLException {
			return new TaskModel11(
					rs.getString("name"),
					rs.getString("fullname"),
					rs.getString("major"),
					rs.getInt("milunit_number")
			);
		}
	}
}
