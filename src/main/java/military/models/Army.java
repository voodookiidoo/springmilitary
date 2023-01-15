package military.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
public class Army {
	private int id,
			commander_id,
			army_number;

	public static class ArmyMapper extends BeanPropertyRowMapper<Army> {

		@Override
		public Army mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Army(
					rs.getInt("id"),
					rs.getInt("commander_id"),
					rs.getInt("army_number")
			);
		}
	}
}
