package military.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
@Data
@AllArgsConstructor
public class TaskModel8 {
	private int num, amount;
	public static class Mapper extends BeanPropertyRowMapper<TaskModel8>
	{
		@Override
		public TaskModel8 mapRow(ResultSet rs, int rowNumber) throws SQLException {
			return new TaskModel8(
					rs.getInt("num"),
					rs.getInt("amount")
			);
		}
	}
}
