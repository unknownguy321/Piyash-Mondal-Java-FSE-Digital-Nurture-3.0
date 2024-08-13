@Service
public class EmployeeService {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;

    // Use primaryJdbcTemplate or secondaryJdbcTemplate for specific operations
}
