// package com.conference.repositories;

// import java.util.Set;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import com.conference.entities.Conference;
// import com.conference.entities.ConferenceUser;

// import jakarta.transaction.Transactional;

// public interface Conference_UserRepo extends JpaRepository<ConferenceUser,
// Integer> {

// @Query("SELECT c FROM ConferenceUser c WHERE c.conference.id =
// :conferenceId")
// Set<ConferenceUser> findByAllConference_id(@Param("conferenceId") int
// conferenceId);

// @Modifying
// @Transactional
// @Query("DELETE FROM ConferenceUser c WHERE c.conference.id = :conferenceId")
// void deleteByAllConference_id(@Param("conferenceId") int conferenceId);
// }
