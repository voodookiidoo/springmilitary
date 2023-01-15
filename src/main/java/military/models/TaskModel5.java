package military.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
public class TaskModel5 {
	private int number;
	private String place;

	public static class Mapper extends BeanPropertyRowMapper<TaskModel5> {
		@Override
		public TaskModel5 mapRow(ResultSet rs, int rowNumber) throws SQLException {
			return new TaskModel5(
					rs.getInt("mil_num"),
					rs.getString("place")
			);
		}
	}
}
