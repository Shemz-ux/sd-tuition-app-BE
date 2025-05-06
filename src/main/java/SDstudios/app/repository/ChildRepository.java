package SDstudios.app.repository;

import SDstudios.app.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Integer> {
    List<Child> findChildByParentParentId(Integer parentId);
}
