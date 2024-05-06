package tn.spring.pispring.ServiceIMP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import tn.spring.pispring.Entities.Address;
import tn.spring.pispring.Entities.Orderr;
import tn.spring.pispring.Entities.Role;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.repo.AddressRepo;
import tn.spring.pispring.repo.RoleRepository;
import tn.spring.pispring.repo.UserRepository;
import tn.spring.pispring.util.FileNamingUtil;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepository;

    @Autowired
    private UserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;

    public Address saveAddresseee(Long userId, Address address) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            address.setUser(user);
            return addressRepository.save(address);
        }
        return null; // Retourner null si l'utilisateur n'est pas trouvé
    }

    public Address saveAddress(Address address) {
        addressRepository.save(address);
        return address;
    }

    // Supprimer une adresse
    public void deleteAddress(int id) {
        addressRepository.deleteById(id);
    }


    public User createUserWithRole(String userName, String email, String password, String zone, int phoneNumber, String roleName) {
        User user = new User();
       // user.setUserName(userName);
        user.setEmail(email);

        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setZone(zone);

        // Rechercher le rôle par son nom
        Role role = roleRepo.findByName(roleName);

        if (role == null) {
            throw new RuntimeException("Le rôle '" + roleName + "' n'existe pas.");
        }

        user.setRole(role);

        return userRepo.save(user);
    }


    public AddressService(FileNamingUtil fileNamingUtil, @Value("${uploadProductImages}") String uploadProductImages) {
        this.fileNamingUtil = fileNamingUtil;
        this.uploadProductImages = uploadProductImages;
    }

    public final FileNamingUtil fileNamingUtil;
    public final String uploadProductImages;

    public User createUserWithRoleee(String userName, String email, String password, String zone, int phoneNumber, String roleName, MultipartFile imageFile) {
        try {
            String fileName = fileNamingUtil.nameFile(imageFile);
            Path destinationPath = Paths.get(uploadProductImages, fileName);
            Files.copy(imageFile.getInputStream(), destinationPath);

            User userr = new User();
           userr.setUsername(userName);
            userr.setEmail(email);
            userr.setPassword(password);
            userr.setPhoneNumber(phoneNumber);
            userr.setZone(zone);
            userr.setImage(fileName);


            // Rechercher le rôle par son nom
            Role role = roleRepo.findByName(roleName);

            if (role == null) {
                throw new RuntimeException("Le rôle '" + roleName + "' n'existe pas.");
            }

            userr.setRole(role);

            return userRepo.save(userr);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde de l'image.", e);
        }
    }


    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }


    public Address createAddress(String rue, String ville, String codePostal, String pays) {
        Address address = new Address();
        address.setRue(rue);
        address.setVille(ville);
        address.setCodePostal(codePostal);
        address.setPays(pays);
        return addressRepository.save(address); // Sauvegarder l'adresse dans la base de données
    }

    public Address createAddress(String rue, String ville, String codePostal, String pays, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé avec l'ID : " + userId));

        Address address = new Address();
        address.setRue(rue);
        address.setVille(ville);
        address.setCodePostal(codePostal);
        address.setPays(pays);
        address.setUser(user);

        return addressRepository.save(address);
    }


    public Address updateAddress(String rue, String ville, String codePostal, String pays, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé avec l'ID : " + userId));

        Address address = addressRepository.findByUserId(userId);

        // Si l'adresse n'existe pas, créez une nouvelle adresse
        if (address == null) {
            address = new Address();
            address.setUser(user);
        }

        address.setRue(rue);
        address.setVille(ville);
        address.setCodePostal(codePostal);
        address.setPays(pays);

        return addressRepository.save(address);
    }
}
