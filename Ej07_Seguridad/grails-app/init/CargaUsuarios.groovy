import com.curso.modelo.entidad.Cliente
import com.curso.modelo.entidad.Role
import com.curso.modelo.entidad.User
import com.curso.modelo.entidad.UserRole
import org.grails.orm.hibernate.HibernateDatastore

Map configuration = [
        'hibernate.hbm2ddl.auto':'update',
        'hibernate.show_sql':'true',
        'dataSource.url':'jdbc:h2:file:C:/h2/bbddGRAILS_Seguridad;USER=sa'
]
HibernateDatastore datastore = new HibernateDatastore( configuration, Cliente, Role, User, UserRole)

datastore.withNewSession({ session ->

    println "========================================"
    session.beginTransaction()

    //n+1 consultas...
    //User.findAll().stream( { u -> u.delete() })

    //Mil veces mejor:
    UserRole.executeUpdate("delete UserRole")
    Role.executeUpdate("delete Role")
    User.executeUpdate("delete User")

    Role rol1 = new Role(authority: "ROLE_USUARIO")
    Role rol2 = new Role(authority: "ROLE_ADMIN")
    rol1.save()
    rol2.save()

    /*
    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    */

    User usr1 = new User(username: "ringo", password: '{bcrypt}$2a$10$pL08RoB7WjeCOcld3fVwluTI0OxzEu6mGQPw8.fhI7sP2XfBATa.K', enabled: true, accountExpired: false, accountLocked: false, passwordExpired: false)
    User usr2 = new User(username: "harry", password: '{bcrypt}$2a$10$BGE.Dc03pjlHfx3iEPd2POIq9CGZy0Bd9d3u4O5OsIhJ4vhQiZo9u', enabled: true, accountExpired: false, accountLocked: false, passwordExpired: false)
    usr1.save()
    usr2.save()

    UserRole usrRole1 = new UserRole(user: usr1, role: rol1)
    UserRole usrRole2 = new UserRole(user: usr2, role: rol2)
    usrRole1.save()
    usrRole2.save()

    session.getTransaction().commit() //.rollback()

})





