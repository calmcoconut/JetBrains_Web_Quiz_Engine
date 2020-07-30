package engine.quiz;

import org.springframework.data.repository.PagingAndSortingRepository;

interface QuizRepository extends PagingAndSortingRepository<Quiz, Integer> {
}