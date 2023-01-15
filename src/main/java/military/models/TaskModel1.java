package military.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
public class TaskModel1 {
	private int army_id,
	tacit_unit_id,
	military_unit_id;
	private String commander;
	public static class Mapper extends BeanPropertyRowMapper<TaskModel1>
	{
		@Override
		public TaskModel1 mapRow(ResultSet rs, int rowNumber) throws SQLException {
			return new TaskModel1(
					rs.getInt("army_id"),
					rs.getInt("tactic_unit_id"),
					rs.getInt("military_unit_id"),
					rs.getString("commander")
			);
		}
	}


}
