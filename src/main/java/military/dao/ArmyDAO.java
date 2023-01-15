package military.dao;

import military.models.Army;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArmyDAO {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public ArmyDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;

	}
	public List<Army> index()
	{

		List<Army> query = jdbcTemplate.query(
				"select id, commander_id, army_number " +
						"from army",
				new Army.ArmyMapper()
		);
		return query;
	}

}
