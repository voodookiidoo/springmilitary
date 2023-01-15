package military.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
public class TaskModel3 {
	private int id;
	private String name;
	public static class Mapper extends BeanPropertyRowMapper<TaskModel3>
	{
		@Override
		public TaskModel3 mapRow(ResultSet rs, int rowNumber) throws SQLException {
			return new TaskModel3(
					rs.getInt("id"),
					rs.getString("name")
			);
		}
	}
}
