import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegistrationData {
    public String login;
    public String password;
    public String status;
}
