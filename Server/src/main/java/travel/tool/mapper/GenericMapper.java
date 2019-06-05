package travel.tool.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ipop
 */
public abstract class GenericMapper<M, E> {

    public abstract M toInternal(E epo);

    public abstract E toExternal(M model);

    public List<M> toInternal(Collection<E> epoList) {
        return epoList.stream()
                .map(this::toInternal)
                .collect(Collectors.toList());
    }

    public List<E> toExternal(Collection<M> modelList) {
        return modelList.stream()
                .map(this::toExternal)
                .collect(Collectors.toList());
    }
}
