package com.product.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
import com.product.api.repository.RepoProductImage;
import com.product.commons.dto.ApiResponse;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

@Service
public class SvcProductImageImp implements SvcProductImage {

	@Autowired
	RepoProductImage repo;
	
	@Value("${app.upload.dir}")
	private String uploadDir;
	
	@Override
	public ApiResponse upload(Integer productId, DtoProductImageIn in) {
		try {
			if (in.getImage().startsWith("data:image")) {
				int commaIndex = in.getImage().indexOf(",");
					if (commaIndex != -1) {
				in.setImage(in.getImage().substring(commaIndex + 1));
				}
			}
			
			byte[] imageBytes = Base64.getDecoder().decode(in.getImage());
			String fileName = UUID.randomUUID().toString() + ".png";
			String relativePath = Paths.get("img", "product", fileName).toString();
			Path imagePath = Paths.get(uploadDir, relativePath);
			
			Files.createDirectories(imagePath.getParent());
			Files.write(imagePath, imageBytes);
			
			ProductImage productImage = new ProductImage();
			productImage.setProductId(productId);
			productImage.setImage(relativePath);
			productImage.setStatus(1);

			repo.save(productImage);

		    return new ApiResponse("La imagen del producto ha sido actualizada");
		}catch (DataAccessException e) {
		    throw new DBAccessException(e);
		}catch (IOException e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el archivo");
		}
	}

	@Override
	public List<ProductImage> getImages(Integer productId) {
		try {
			return repo.findByProduct_id(productId);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ApiResponse deleteImage(Integer productId, Integer productImageId) {
		try {
			Optional<ProductImage> imageOpt = repo.findById(productImageId);
			
			if (imageOpt.isEmpty()) {
				throw new ApiException(HttpStatus.NOT_FOUND, "La imagen no existe");
			}
			
			ProductImage image = imageOpt.get();
			
			if (!image.getProductId().equals(productId)) {
				throw new ApiException(HttpStatus.FORBIDDEN, "La imagen no pertenece al producto especificado");
			}

			Path imagePath = Paths.get(uploadDir, image.getImage());
			Files.deleteIfExists(imagePath);
			
			repo.delete(image);
			
			return new ApiResponse("La imagen ha sido eliminada");
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		} catch (IOException e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el archivo");
		}
	}
}