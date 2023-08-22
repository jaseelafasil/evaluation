import com.example.data.database.dbQuery
import com.example.data.database.tables.SchemeTable
import com.example.data.database.tables.SchemeTableEntity
import com.example.data.database.tables.toSchemeDetails
import com.example.data.models.SchemeDetails
import com.example.domain.interfaces.SchemeDao
import org.jetbrains.exposed.sql.selectAll

class SchemeDaoImpl :SchemeDao{
    override suspend fun addSchemeDetails(schemeDetails: Array<SchemeDetails>): String? = dbQuery {
        if(SchemeTable.selectAll().empty()){
                schemeDetails.forEach { scheme ->
                    println("data is adding ************************")
                    SchemeTableEntity.new {
                        schemeCode = scheme.schemeCode
                        schemeName = scheme.schemeName
                    }
                }
        }
        "Scheme details available"
    }

    override suspend fun searchSchemeByName(sName: String): List<SchemeDetails> = dbQuery {
        SchemeTableEntity.find { SchemeTable.schemeName.like("%$sName%") }.map { it.toSchemeDetails() }
    }

    override suspend fun validateSchemeId(schemeId: String): SchemeTableEntity? = dbQuery {
        SchemeTableEntity.find { SchemeTable.schemeCode eq schemeId }.firstOrNull()
    }

}