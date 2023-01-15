package military.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
@Data
@AllArgsConstructor
public class TaskModel10 {

	public static class Mapper extends BeanPropertyRowMapper<TaskModel10>
	{
		@Override
		public TaskModel10 mapRow(ResultSet rs, int rowNumber) throws SQLException {
			return super.mapRow(rs, rowNumber);
		}
	}
}
