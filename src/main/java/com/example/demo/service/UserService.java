public interface UserService {
    User create(User user);
    User get(Long id);
    List<User> getAll();
    User update(Long id, User user);
    void delete(Long id);
}
