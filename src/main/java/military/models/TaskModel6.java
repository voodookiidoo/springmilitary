package military.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
public class TaskModel6 {
	private int amount, num;
	private String type;

	public static class Mapper extends BeanPropertyRowMapper<TaskModel6> {
		@Override
		public TaskModel6 mapRow(ResultSet rs, int rowNumber) throws SQLException {
			return new TaskModel6(
					rs.getInt("amount"),
					rs.getInt("milunit_number"),
					rs.getString("tech_type")
			);
		}
	}
}
