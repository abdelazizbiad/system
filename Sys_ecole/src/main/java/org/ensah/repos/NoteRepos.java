package org.ensah.repos;

import org.ensah.domains.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NoteRepos extends JpaRepository<Note, Long>{

}
