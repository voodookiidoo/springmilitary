package military.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
public class TaskModel12 {
	private int id, amount;
	private String type;
	public static class Mapper extends BeanPropertyRowMapper<TaskModel12>
	{
		@Override
		public TaskModel12 mapRow(ResultSet rs, int rowNumber) throws SQLException {
			return new TaskModel12(
					rs.getInt("id"),
					rs.getInt("amount"),
					rs.getString("type")
			);
		}
	}
}
