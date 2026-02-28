package metiers;

import entities.Module;
import entities.UniteEnseignement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModuleBusiness {

    private static List<Module> modules = new ArrayList<>();
    private static boolean initialized = false;

    private UniteEnseignementBusiness uniteEnseignementBusiness = new UniteEnseignementBusiness();

    public ModuleBusiness() {

        if (!initialized) {

            modules.add(new Module("M101", "Algorithmique", 3, 30,
                    Module.TypeModule.PROFESSIONNEL,
                    uniteEnseignementBusiness.getUEByCode(1)));

            modules.add(new Module("M102", "Base de données", 2, 20,
                    Module.TypeModule.PROFESSIONNEL,
                    uniteEnseignementBusiness.getUEByCode(1)));

            modules.add(new Module("M201", "Communication", 1, 15,
                    Module.TypeModule.TRANSVERSAL,
                    uniteEnseignementBusiness.getUEByCode(2)));

            initialized = true;
        }
    }

    // Ajouter un module
    public boolean addModule(Module module) {

        int code = module.getUniteEnseignement().getCode();
        UniteEnseignement ue = uniteEnseignementBusiness.getUEByCode(code);

        if (ue != null) {
            module.setUniteEnseignement(ue);
            return modules.add(module);
        }
        return false;
    }

    // Récupérer un module par matricule
    public Module getModuleByMatricule(String matricule) {
        for (Module m : modules) {
            if (m.getMatricule().equals(matricule)) {
                return m;
            }
        }
        return null;
    }

    // Récupérer tous les modules
    public List<Module> getAllModules() {
        return modules;
    }

    // Récupérer les modules par type
    public List<Module> getModulesByType(Module.TypeModule type) {
        List<Module> result = new ArrayList<>();

        for (Module m : modules) {
            if (m.getType() == type) {
                result.add(m);
            }
        }

        return result;
    }

    // Mettre à jour un module (VERSION SÉCURISÉE)
    public boolean updateModule(String matricule, Module updatedModule) {

        Module existing = getModuleByMatricule(matricule);

        if (existing != null) {

            existing.setNom(updatedModule.getNom());
            existing.setCoefficient(updatedModule.getCoefficient());
            existing.setVolumeHoraire(updatedModule.getVolumeHoraire());
            existing.setType(updatedModule.getType());

            int code = updatedModule.getUniteEnseignement().getCode();
            UniteEnseignement ue = uniteEnseignementBusiness.getUEByCode(code);
            existing.setUniteEnseignement(ue);

            return true;
        }

        return false;
    }
    // Supprimer un module
    public boolean deleteModule(String matricule) {

        Iterator<Module> iterator = modules.iterator();

        while (iterator.hasNext()) {
            Module m = iterator.next();

            if (m.getMatricule().equals(matricule)) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }

    // Récupérer modules d'une UE
    public List<Module> getModulesByUE(UniteEnseignement ue) {

        List<Module> result = new ArrayList<>();

        for (Module m : modules) {
            if (m.getUniteEnseignement() != null &&
                    m.getUniteEnseignement().getCode() == ue.getCode()) {
                result.add(m);
            }
        }

        return result;
    }
}
