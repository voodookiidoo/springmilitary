package military.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
public class TaskModel9 {
	private String type, descr, name;
	private int amount;

	public static class Mapper extends BeanPropertyRowMapper<TaskModel9> {
		@Override
		public TaskModel9 mapRow(ResultSet rs, int rowNumber) throws SQLException {
			return new TaskModel9(
					rs.getString("type"),
					rs.getString("descr"),
					rs.getString("disname"),
					rs.getInt("amount")
			);
		}
	}
}
