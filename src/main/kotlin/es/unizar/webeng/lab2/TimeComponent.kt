package es.unizar.webeng.lab2

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

/** Payload returned by GET /time. */
data class TimeDTO(
    val time: LocalDateTime,
)

/** Abstraction over the clock so tests can inject a fixed time. */
interface TimeProvider {
    fun now(): LocalDateTime
}

/** Default TimeProvider backed by the system clock. */
@Service
class TimeService : TimeProvider {
    override fun now(): LocalDateTime = LocalDateTime.now()
}

/** Maps a LocalDateTime to its DTO representation. */
fun LocalDateTime.toDTO(): TimeDTO = TimeDTO(time = this)

/** Exposes GET /time, returning the current server time as JSON. */
@RestController
class TimeController(
    private val service: TimeProvider,
) {
    @GetMapping("/time")
    fun time(): TimeDTO = service.now().toDTO()
}
