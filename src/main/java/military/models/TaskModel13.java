package military.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
@Data
@AllArgsConstructor
public class TaskModel13 {
	private int id, amount;
	public static class Mapper extends BeanPropertyRowMapper<TaskModel13>
	{
		@Override
		public TaskModel13 mapRow(ResultSet rs, int rowNumber) throws SQLException {
			return new TaskModel13(
					rs.getInt("id"),
					rs.getInt("amount")
			);
		}
	}
}
