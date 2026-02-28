package ressources;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static java.lang.Integer.parseInt;

@Path("UE")
public class RestUE {

    private static UniteEnseignementBusiness UEB = new UniteEnseignementBusiness();


    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response createUE(UniteEnseignement ue) {
        if (UEB.addUniteEnseignement(ue)) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveUE(@QueryParam("semestre") String semestre) {
        List<UniteEnseignement> l;
        if(semestre!=null)
        {
            l=UEB.getUEBySemestre(parseInt(semestre));
        }else
        {
            l=UEB.getListeUE();
        }
        if(l !=null && l.isEmpty()==false)
        {

            return Response.status(Response.Status.OK).entity(l).build();
        }else
        {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveUEBySemestre(@QueryParam("semestre") int semestre) {
        List<UniteEnseignement> l =UEB.getUEBySemestre(semestre);
        if(l !=null && !l.isEmpty())
        {

            return Response.status(Response.Status.OK).entity(l).build();
        }else
        {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }*/

    @Path("/{id}")
    @DELETE
    public Response DeleteUE(@PathParam("id") int code)
    {
        if (UEB.deleteUniteEnseignement(code)) {
            return Response.status(Response.Status.OK).build();
        }
        else
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @PUT
    @Path("/{code}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response updateUE(@PathParam("code") int code, UniteEnseignement ue) {
        if (UEB.updateUniteEnseignement(code, ue)) {
            return Response.status(Response.Status.OK).build();
        }else
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }



}