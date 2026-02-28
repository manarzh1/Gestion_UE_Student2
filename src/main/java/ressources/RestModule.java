package ressources;

import entities.Module;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/modules")
public class RestModule {

    public static ModuleBusiness moduleBusiness = new ModuleBusiness();

    // GET ALL
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Module> getAllModules() {
        return moduleBusiness.getAllModules();
    }

    // GET by matricule
    @GET
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModule(@PathParam("matricule") String matricule) {

        Module m = moduleBusiness.getModuleByMatricule(matricule);

        if (m != null)
            return Response.ok(m).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    // POST
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
   // @Produces(MediaType.APPLICATION_JSON)
    public Response addModule(Module module) {

        if (moduleBusiness.addModule(module))
            return Response.status(Response.Status.CREATED).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    // PUT
    @PUT
    @Path("/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateModule(@PathParam("matricule") String matricule,
                                 Module module) {

        if (moduleBusiness.updateModule(matricule, module))
            return Response.status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    // DELETE
    @DELETE
    @Path("/{matricule}")
    public Response deleteModule(@PathParam("matricule") String matricule) {

        if (moduleBusiness.deleteModule(matricule))
            return Response.status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}
