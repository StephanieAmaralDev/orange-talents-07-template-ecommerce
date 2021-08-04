package br.com.zupacademy.stephanie.ecommerce.validacao;

        import javax.persistence.EntityManager;
        import javax.persistence.Query;
        import javax.validation.ConstraintValidator;
        import javax.validation.ConstraintValidatorContext;
        import java.util.List;

public class ValidadorUnico implements ConstraintValidator<ValorUnico, Object> {
    private String field;
    private Class<?> entity;

    private EntityManager em;

    public ValidadorUnico(EntityManager em) {
        this.em = em;
    }

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.entity = constraintAnnotation.entity();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String jpql = "SELECT c FROM " +entity.getName()+ " c WHERE " +field+ "= :pValue";
        Query query = em.createQuery(jpql);
        query.setParameter("pValue", value);
        List<?> resultList = query.getResultList();

        return resultList.isEmpty();
    }
}
